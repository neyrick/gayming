var persist = require("persist");
var type = persist.type;

var setting = persist.define("setting", {
          "name": type.STRING,
          "code": type.STRING,
          "mode": type.INTEGER,
          "status": type.INTEGER
    }, { tableName: "setting" });
    
var game = persist.define("game", {
          "dayid": type.INTEGER,
          "timeframe": type.STRING,
    }, { tableName: "game" }).hasOne(setting, { createHasMany: false, foreignKey : "setting"});
    
var schedule = persist.define("schedule", {
          "dayid": type.INTEGER,
          "timeframe": type.STRING,
          "player": type.STRING,
          "role": type.STRING
    }, { tableName: "schedule" }).hasOne(setting, { createHasMany: false, foreignKey : "setting"}).hasOne(game, { createHasMany: false, foreignKey : "game"});
    
var comment = persist.define("comment", {
          "dayid": type.INTEGER,
          "timeframe": type.STRING,
          "player": type.STRING,
          "message": type.STRING
    }, { tableName: "comment" }).hasOne(setting, { createHasMany: false, foreignKey : "setting"});

exports.setting = setting;
exports.game = game;
exports.schedule = schedule;
exports.comment = comment;
