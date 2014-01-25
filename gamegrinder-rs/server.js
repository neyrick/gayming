var persist = require("persist");

var settingsModule = require("./entities/setting.js");

var restify = require('restify');

function respond(req, res, next) {
  res.send('Parametres: ' + req.params);
}

persist.connect(
  function(err, connection) {
    settingsModule.init(connection);
});

var server = restify.createServer();
server.get('/gg/setting', settingsModule.fetchAll);
server.listen(8080, function() {
    console.log('Démarrage de l\'écoute de', server.name, server.url);
});
