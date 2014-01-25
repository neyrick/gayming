var persist = require("persist");
var type = persist.type;
var em = persist.define("Setting", {
          "name": type.STRING,
          "code": type.STRING,
          "mode": type.INTEGER
    }, { tableName: "setting" });
//	    console.log('Initialisation: ' + this.em);
    
var connection;

module.exports = {
    
    init : function(conn) {
        connection = conn;
    },

    fetchAll : function(req, res, next) {
            var result = new Array();
	    console.log('Em: ' + this.em);
	    console.log('This: ' + this);
            em.using(connection).each(function(err, setting) {
                result.push(setting);
            }, function() {
              res.send(result);
              next();
            });
    }
}

