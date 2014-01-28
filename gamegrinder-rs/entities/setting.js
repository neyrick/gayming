var persist = require("persist");
var type = persist.type;
var setting = persist.define("setting", {
          "name": type.STRING,
          "code": type.STRING,
          "mode": type.INTEGER,
          "status": type.INTEGER
    }, { tableName: "setting" });
//	    console.log('Initialisation: ' + this.em);
    
var connection;

    exports.init = function(conn) {
        connection = conn;
    },

    exports.fetchAll = function(req, res, next) {
            var result = new Array();
            setting.using(connection).each(function(err, setting) {
                result.push(setting);
            }, function() {
              res.send(result);
              next();
            });
    },

    exports.create = function(req, res, next) {
            if (!req.params.setting) {
                req.log.warn('create: missing setting');
                next();
                return;
            }
            var item = new setting(req.params.setting);
            item.save(connection, function(err) {
               if (err) res.send("Error: " + err);
               else res.send(item);
              next();
            });
    },

    exports.update = function(req, res, next) {
            if (!req.params.setting) {
                req.log.warn('update: missing setting');
                next();
                return;
            }
            var item = req.params.setting;
            var itemid = item.id;
            delete item.id;
            setting.update(connection, itemid, item, function(err) {
               if (err) res.send("Error: " + err);
               else res.send("Update OK");
              next();
            });
    },

    exports.delete = function(req, res, next) {
            var item = new setting({ id: req.params.setting });
            item.delete(connection, function(err) {
               res.send("Delete OK");
              next();
            });
    }

