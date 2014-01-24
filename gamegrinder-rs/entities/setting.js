var persist = require("persist");
var type = persist.type;

module.exports = function(conn) {
    return {
        em : persist.define("Setting", {
          "name": type.STRING,
          "code": type.STRING,
          "mode": type.INTEGER,
        }, { tableName: "setting" }),
        connection: conn,
        fetchAll : function() {
            this.em.using(this.connection).each(function(err, setting) {
                console.log('Setting: ' + JSON.stringify(setting));
            }, function() {});
        },
    };
}

