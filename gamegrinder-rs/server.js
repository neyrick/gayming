var persist = require("persist");

var entities = require("./entities");
var settingsModule = entities.Setting;

var restify = require('restify');

function respond(req, res, next) {
  res.send('Parametres: ' + req.params);
}

persist.connect(
  function(err, connection) {
    settingsModule.init(connection);
});

var server = restify.createServer();

server.use(restify.acceptParser(server.acceptable));
server.use(restify.authorizationParser());
server.use(restify.dateParser());
server.use(restify.queryParser());
server.use(restify.jsonp());
server.use(restify.gzipResponse());
server.use(restify.bodyParser());

server.get('/gg/setting', settingsModule.fetchAll);
server.get('/gg/setting/:code', settingsModule.findByCode);
server.put('/gg/setting', settingsModule.create);
server.post('/gg/setting', settingsModule.update);
server.del('/gg/setting/:setting', settingsModule.delete);
server.on('uncaughtException', function (request, response, route, error) {
    console.log('Erreur !!! %j', error);
});
server.listen(5000, function() {
    console.log('Démarrage de l\'écoute de', server.name, server.url);
});
