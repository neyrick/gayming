var entities = require("./entities");

var persist = require("persist");

var setting = entities.setting;
var comment = entities.comment;
var game = entities.game;
var schedule = entities.schedule;
var history = entities.history;

var connection;

persist.connect(
  function(err, conn) {
    connection = conn;
});

function assignGame(idgame, masterschedule, players, callback) {
	var updateStatement = 'UPDATE schedule SET game = ? WHERE id IN ( ?';
	var params = [idgame, masterschedule];
	for (name in players) {
		updateStatement = updateStatement + ', ?';
		params.push(players[name].id);
	}
	updateStatement = updateStatement + ')';
	connection.runSql(updateStatement, params, callback);
}

function storelog(logdata) {
	new history(logdata).save(connection, function(err) {
               if (err) console.log("Error: " + err);
            });
}

function createBaseLogData(req, source) {
	var result = { action : req.params['log_action'], address : req.connection.remoteAddress }
	if (typeof source != "undefined") {
		result.dayid = source.dayid;
		result.tstamp = new Date();
		result.timeframe = source.timeframe;
		result.setting = source.setting;
		result.player = source.player;
	}
	return result;
}

function genericFetchInterval(req, res, next, entity) {
	var basequery = '1=1';
	var params = Array();
	if (req.params.player) params.push(req.params.player);
	if (req.params.minday) params.push(req.params.minday);
	if (req.params.maxday) params.push(req.params.maxday);

	var paramindex = 1;
	if (req.params.player) {
		basequery = basequery + ' and player = $' + paramindex;
		paramindex++;
	}
	if (req.params.setting) {
		basequery = basequery + ' and setting = $' + paramindex;
		paramindex++;
	}
	if (req.params.minday) {
		if (req.params.maxday) {
			basequery = basequery + ' and dayid >= $' + paramindex + ' and dayid <= $' + (paramindex+1);
			paramindex += 2;
		}
		else {
			basequery = basequery + ' and dayid >= $' + paramindex;
			paramindex++;
		}
	}
	else if (req.params.maxday) {
		basequery = basequery + ' and dayid <= $' + paramindex;
		paramindex++;
	}
        entity.where(basequery, params).all(connection, function(err, result) {
		        if (err) console.log("Erreur: " + err);
			res.send(result);
			next();
	});
}

function genericSendJson(res, data) {
    res.charSet('UTF-8');
    res.send(data);
}

function genericCreate(req, res, next, entity) {
            entity.save(connection, function(err) {
               if (err) res.send("Error: " + err);
               else genericSendJson(res, entity);
              next();
            });
}

function genericUpdate(req, res, next, entityManager) {
            var item = req.body;
            var itemid = item.id;
            delete item.id;
            entityManager.update(connection, itemid, item, function(err) {
               if (err) res.send("Error: " + err);
               else res.send("Update OK");
              next();
            });
}

function genericDelete(req, res, next, entity) {
            entity.delete(connection, function(err) {
               res.send("Delete OK");
              next();
            });
}

    exports.init = function(conn) {
        connection = conn;
    };

    
    exports.fetchHistory = function(req, res, next) {
            history.where({dayid : req.params.dayid, timeframe : req.params.timeframe, setting : req.params.setting}).orderBy('tstamp', persist.Descending).all(connection, function(err, result) {
              genericSendJson(res, result);
              next();
            });
    };

    exports.fetchAllSettings = function(req, res, next) {
            var result = new Array();
            setting.using(connection).each(function(err, setting) {
                result.push(setting);
            }, function() {
              genericSendJson(res, result);
              next();
            });
    };

    exports.findSettingByCode = function(req, res, next) {
            var result = new Array();
            setting.using(connection).where({ code: req.params.code}).each(function(err, setting) {
                result.push(setting);
            }, function() {
              genericSendJson(res, result);
              next();
            });
    };

    exports.createSetting = function(req, res, next) {
	    genericCreate(req, res, next, new setting(req.body));	
    };

    exports.updateSetting = function(req, res, next) {
	    genericUpdate(req, res, next, setting);
    };

    exports.createSchedule = function(req, res, next) {
        // Verification que le joueur est bien disponible
        var newSchedule = req.body;
        var conflict = false;
        var querySchedule = { dayid : newSchedule.dayid, timeframe : newSchedule.timeframe, player : newSchedule.player};
        schedule.using(connection).where(querySchedule).each(function(err, sameschedule) {
            if (sameschedule._game != null) conflict = true;
        }, function(err) {
            if (conflict) {
                console.log("Conflit détecté !");
            }
            else {
                genericCreate(req, res, next, new schedule(req.body));
		var logdata = createBaseLogData(req, req.body);
		logdata.data = { role : req.body.role };
		storelog(logdata);
            }
            next();
        });
    };
/*
    exports.deleteSchedule = function(req, res, next) {
        schedule.where(JSON.parse(req.body)).deleteAll(connection, function(err) {
            if (err) res.send("Erreur: " + err);
		else res.send("OK");
            next();
        });
    };
*/
    exports.deleteSchedule = function(req, res, next) {
	schedule.getById(connection, req.params.idschedule, function(err, targetschedule) {
		if (err) res.send("Erreur: " + err);
		else {
			var logdata = createBaseLogData(req, targetschedule);
			logdata.data = { role : targetschedule.role };
			if (targetschedule.game != null) {
				schedule.where( {game : targetschedule._game}).all(connection, function(err, players) {
					if (err) res.send("Erreur: " + err);
					else {
						logdata.data.players = players;
						targetschedule.delete(connection, function(err) {
							if (err) res.send("Erreur: " + err);
							else {
								storelog(logdata);
								res.send("OK");
							}
						});
					}
				});
			}
			else {
				targetschedule.delete(connection, function(err) {
					if (err) res.send("Erreur: " + err);
					else {
						storelog(logdata);
						res.send("OK");
					}
				});
			}
		}
		next();
	});
    };

    exports.fetchSchedule = function(req, res, next) {
	genericFetchInterval(req, res, next, schedule);
    }

    exports.fetchComment = function(req, res, next) {
	genericFetchInterval(req, res, next, comment);
    }

    exports.setComment = function(req, res, next) {
	var comm;
	var logdata = createBaseLogData(req, req.body);
	logdata.data = { message : req.body.message };
	if ((req.body.id != null) && (req.body.id > 0)) {
		comm = new comment({ id : req.body.id, message : req.body.message});
		if ((comm.message == null) || (comm.message == '')) {
			storelog(logdata);
			genericDelete(req, res, next, comm);
		}
		else {
			storelog(logdata);
			genericUpdate(req, res, next, comment);
		}
	}
	else {
		comm = new comment(req.body);
		if ((comm.message == null) || (comm.message == '')) {
			next();
		}
		else {
			storelog(logdata);
			genericCreate(req, res, next, new comment(req.body));
		}
	}
    }

    exports.fetchGame = function(req, res, next) {
	genericFetchInterval(req, res, next, game);
    }

    exports.fetchPlanning = function(req, res, next) {
	var basequery = "SELECT s.id AS idschedule, COALESCE(s.dayid, c.dayid) AS dayid, COALESCE(s.timeframe, c.timeframe) AS timeframe, COALESCE(s.setting, c.setting) AS setting, s.game , COALESCE(s.player, c.player) AS player, s.role, c.id AS idcomment,  c.message FROM schedule s FULL OUTER JOIN comment c USING (dayid, timeframe, setting, player) WHERE ((s.dayid >= $1) OR (c.dayid >= $1)) AND ((s.dayid <= $2) OR (c.dayid <= $2))";

	var minday = req.params.minday;
	if (typeof minday == "undefined") minday = 0;
	var maxday = req.params.maxday;
	if (typeof maxday == "undefined") maxday = 99999999;
//	var basequery = 'dayid >= $1 and dayid <= $2';
	var params = [ minday, maxday ];

	var paramindex = 3;
	if (req.params.timeframe) {
		basequery = basequery + ' AND ((s.timeframe = $' + paramindex + ') OR ( c.timeframe = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.timeframe);
	}

	if (req.params.setting) {
		basequery = basequery + ' AND ((s.setting = $' + paramindex + ') OR ( c.setting = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.setting);
	}

	if (req.params.player) {
		basequery = basequery + ' AND ((s.player = $' + paramindex + ') OR ( c.player = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.player);
	}
	
	connection.runSqlAll(basequery, params, function(err, results) {
	        if (err) console.log("Erreur: " + err);
		res.send(results);
		next();
	});
    }

    exports.createGame = function(req, res, next) {
	schedule.getById(connection, req.body.masterschedule, function(err, masterschedule) {
		if (err) res.send("Erreur: " + err);
		else {
			var newgame = new game({ masterschedule : req.body.masterschedule});
			newgame.save(connection, function(err) {
				if (err) res.send("Error: " + err);
				else {
					var nametab = Object.keys(req.body.players);
					assignGame(newgame.id, newgame.masterschedule, req.body.players, function(err) {
						if (err) res.send("Erreur: " + err);
						else {
							var logdata = createBaseLogData(req, masterschedule);
							logdata.data = { players : req.body.players };
							res.send("OK");
							storelog(logdata);
						}
					});
				}
			});
	       }
	});
	next();
    };

    exports.reformGame = function(req, res, next) {
	 schedule.where( {game : req.params.idgame}).all(connection, function(err, oldplayers) {
		if (err) res.send("Erreur: " + err);
		else {
			var dumpedplayers = {};
			var keptplayers = {};
			var newplayers = {};
			var masterschedule;
			for(var i = 0; i < oldplayers.length; i++) {
				if (oldplayers[i].role == 'GM') {
					masterschedule = oldplayers.splice(i, 1)[0];
				}
			}
			var playercopy;
			for(var i = 0; i < oldplayers.length; i++) {
				playercopy = { name : oldplayers[i].player, id : oldplayers[i].id};
				if (typeof req.body.players[oldplayers[i].player] == "undefined") dumpedplayers[oldplayers[i].player] = playercopy;
				else keptplayers[oldplayers[i].player] = playercopy;
			}

			for (name in req.body.players) {
				if (typeof keptplayers[name] == "undefined") newplayers[name]=req.body.players[name];
			}

			var nametab = Object.keys(req.body.players);
			assignGame(null, -1, dumpedplayers, function(err) {
				if (err) res.send("Erreur: " + err);
				else {
					assignGame(req.params.idgame, -1, newplayers, function(err) {
						if (err) res.send("Erreur: " + err);
						else {
							var logdata = createBaseLogData(req, masterschedule);
							logdata.data = { dumped : dumpedplayers, kept : keptplayers, added : newplayers };
							res.send("OK");
							storelog(logdata);
						}
					});
				}
			});
	       }
	});
	next();
    }
    
    exports.fetchUpdates = function(req, res, next) {
	var basequery = "SELECT dayid, timeframe, setting, MAX(tstamp) AS update FROM history h  WHERE (h.dayid >= $1) AND (h.dayid <= $2)";

	var minday = req.params.minday;
	if (typeof minday == "undefined") minday = 0;
	var maxday = req.params.maxday;
	if (typeof maxday == "undefined") maxday = 99999999;
	var params = [ minday, maxday ];

	var paramindex = 3;
	if (req.params.timeframe) {
		basequery = basequery + ' AND ((s.timeframe = $' + paramindex + ') OR ( c.timeframe = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.timeframe);
	}

	if (req.params.setting) {
		basequery = basequery + ' AND ((s.setting = $' + paramindex + ') OR ( c.setting = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.setting);
	}

	if (req.params.player) {
		basequery = basequery + ' AND ((s.player = $' + paramindex + ') OR ( c.player = $' + paramindex + '))';
		paramindex++;
		params.push(req.params.player);
	}
	
	basequery = basequery + ' GROUP BY dayid, timeframe, setting';
	
	connection.runSqlAll(basequery, params, function(err, results) {
	        if (err) console.log("Erreur: " + err);
		res.send(results);
		next();
	});
    }
