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
//server.del('/gg/setting/:setting', serv.deleteSetting);

server.get('/gg/schedule', serv.fetchSchedule);
server.get('/gg/schedule/:player', serv.fetchSchedule);
server.put('/gg/schedule', serv.createSchedule);
server.del('/gg/schedule', serv.deleteSchedule);

server.get('/gg/comment', serv.fetchComment);
server.get('/gg/comment/:player', serv.fetchComment);

server.get('/gg/game', serv.fetchGame);

server.get('/gg/planning', serv.fetchPlanning);


server.on('uncaughtException', function (request, response, route, error) {
    console.log('Erreur !!! %j', error);
});
server.listen(5000, function() {
    console.log('Démarrage de l\'écoute de', server.name, server.url);
});
