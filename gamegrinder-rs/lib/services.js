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
                next();
            }
            else {
                genericCreate(req, res, next, new schedule(req.body));
            }
        });	    
    };

    exports.deleteSchedule = function(req, res, next) {
        schedule.where(JSON.parse(req.body)).deleteAll(connection, function(err) {
            if (err) console.log("Erreur: " + err);
            res.send("OK");
            next();
        });
    };

    exports.fetchSchedule = function(req, res, next) {
	genericFetchInterval(req, res, next, schedule);
    }

    exports.fetchComment = function(req, res, next) {
	genericFetchInterval(req, res, next, comment);
    }

    exports.fetchGame = function(req, res, next) {
	genericFetchInterval(req, res, next, game);
    }

    exports.fetchPlanning = function(req, res, next) {
	var minday = req.params.minday;
	if (typeof minday == "undefined") minday = 0;
	var maxday = req.params.maxday;
	if (typeof maxday == "undefined") maxday = 99999999;
	var basequery = 'dayid >= $1 and dayid <= $2';
	var params = [ minday, maxday ];

	var paramindex = 3;
	if (req.params.timeframe) {
		basequery = basequery + ' and timeframe = $' + paramindex;
		paramindex++;
		params.push(req.params.timeframe);
	}

	if (req.params.setting) {
		basequery = basequery + ' and setting = $' + paramindex;
		paramindex++;
		params.push(req.params.setting);
	}

	var gamesparams = params.slice();
	var gamesquery = basequery;

	if (req.params.player) {
		basequery = basequery + ' and player = $' + paramindex;
		paramindex++;
		params.push(req.params.player);
	}
	
	connection.chain({
		schedule: schedule.where(basequery, params).all,
		comments: comment.where(basequery, params).all,
		games: game.where(gamesquery, gamesparams).all,
	}, function(err, results) {
	        if (err) console.log("Erreur: " + err);
		res.send(results);
		next();
	});
	next();
    }

    exports.createComment = function(req, res, next) {
	    genericCreate(req, res, next, new comment(req.body));
    };

    exports.createGame = function(req, res, next) {
	    genericCreate(req, res, next, new game(req.body));
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

    exports.deleteGame = function(req, res, next) {
	    genericDelete(req, res, next, new game({ id: req.params.id }));
    };
