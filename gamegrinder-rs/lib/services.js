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
            console.log("Erreur: " + err);
            console.log(sameschedule);
            if (sameschedule.game) conflict = true;
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
