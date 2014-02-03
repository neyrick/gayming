var entities = require("./entities");
var persist = require("persist");

var setting = entities.setting;
var comment = entities.comment;
var game = entities.game;
var schedule = entities.schedule;

var connection;

persist.connect(
  function(err, conn) {
    connection = conn;
});

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
            }
            next();
        });	    
    };

    exports.deleteSchedule = function(req, res, next) {
        schedule.where(JSON.parse(req.body)).deleteAll(connection, function(err) {
            if (err) res.send("Erreur: " + err);
		else res.send("OK");
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
	var comm = new comment(req.body);
	if ((comm.message == null) || (comm.message == '')) {
		if ((comm.id != null) && (comm.id > 0)) {
			genericDelete(req, res, next, comm);
		}
		else next();
	}	
	else {
		if ((comm.id != null) && (comm.id > 0)) {
			genericUpdate(req, res, next, comment);
		}
		else genericCreate(req, res, next, new comment(req.body));
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
//		schedule: schedule.include(game).where(basequery, params).all,
//		comments: comment.where(basequery, params).all,
//	, function(err, results) {
	        if (err) console.log("Erreur: " + err);
		res.send(results);
		next();
	});
    }

    exports.createGame = function(req, res, next) {
	    var newgame = new game(req.body);
            newgame.save(connection, function(err) {
               if (err) res.send("Error: " + err);
               else {
			schedule.update( connection, newgame.masterschedule, { game : newgame.id }, function(err) {
				if (err) res.send("Erreur: " + err);
				else res.send("OK");
			});
	       }
              next();
            });
    };

    exports.deleteGame = function(req, res, next) {
	    genericDelete(req, res, next, new game({ id: req.params.id }));
    };

    exports.createComment = function(req, res, next) {
	    genericCreate(req, res, next, new comment(req.body));
    };

    exports.updateComment = function(req, res, next) {
	    genericUpdate(req, res, next, comment);
    };
    
    exports.updateSchedule = function(req, res, next) {
	    genericUpdate(req, res, next, schedule);
    };
    
    exports.updateGame = function(req, res, next) {
	    genericUpdate(req, res, next, game);
    };
    
    exports.deleteSetting = function(req, res, next) {
	    genericDelete(req, res, next, new setting({ id: req.params.id }));
    };

    exports.deleteComment = function(req, res, next) {
	    genericDelete(req, res, next, new comment({ id: req.params.id }));
    };

