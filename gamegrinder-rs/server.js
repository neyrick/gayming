var serv = require("./lib/services");

var restify = require('restify');

var server = restify.createServer();

server.use(restify.acceptParser(server.acceptable));
server.use(restify.authorizationParser());
server.use(restify.dateParser());
server.use(restify.queryParser());
server.use(restify.jsonp());
server.use(restify.gzipResponse());
server.use(restify.bodyParser());

server.get('/gg/setting', serv.fetchAllSettings);
server.get('/gg/setting/:code', serv.findSettingByCode);
server.put('/gg/setting', serv.createSetting);
server.post('/gg/setting', serv.updateSetting);
server.del('/gg/setting/:setting', serv.deleteSetting);
server.on('uncaughtException', function (request, response, route, error) {
    console.log('Erreur !!! %j', error);
});
server.listen(5000, function() {
    console.log('Démarrage de l\'écoute de', server.name, server.url);
});
