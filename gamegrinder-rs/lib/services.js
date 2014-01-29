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

function genericCreate(req, res, next, entity) {
            entity.save(connection, function(err) {
               if (err) res.send("Error: " + err);
               else res.send(item);
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
              res.send(result);
              next();
            });
    };

    exports.findSettingByCode = function(req, res, next) {
            var result = new Array();
            setting.using(connection).where({ code: req.params.code}).each(function(err, setting) {
                result.push(setting);
            }, function() {
              res.send(result);
              next();
            });
    };

    exports.createSetting = function(req, res, next) {
	    genericCreate(req, res, next, new setting(req.body));
    };

    exports.createComment = function(req, res, next) {
	    genericCreate(req, res, next, new comment(req.body));
    };

    exports.createSchedule = function(req, res, next) {
	    genericCreate(req, res, next, new schedule(req.body));
    };

    exports.createGame = function(req, res, next) {
	    genericCreate(req, res, next, new game(req.body));
    };

    exports.updateSetting = function(req, res, next) {
	    genericUpdate(req, res, next, setting);
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

    exports.deleteSchedule = function(req, res, next) {
	    genericDelete(req, res, next, new schedule({ id: req.params.id }));
    };

    exports.deleteGame = function(req, res, next) {
	    genericDelete(req, res, next, new game({ id: req.params.id }));
    };
