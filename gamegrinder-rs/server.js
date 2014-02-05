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
server.del('/gg/schedule/:idschedule', serv.deleteSchedule);

server.post('/gg/comment', serv.setComment);

server.put('/gg/game', serv.createGame);
//server.get('/gg/game', serv.fetchGame);
server.post('/gg/game/:idgame', serv.reformGame);

server.get('/gg/planning', serv.fetchPlanning);

/*
server.on('after', function (request, response, route, error) {
	if (error) {
		console.log('Erreur: %j', error);
	}
	else {
		var logdata = request.gglogdata;
		if (typeof logdata != "undefined") {
			serv.log(logdata);
		}
	}
});
*/
/*
server.on('uncaughtException', function (request, response, route, error) {
    console.log('Erreur !!! %j', error);
});
*/
server.listen(5000, function() {
    console.log('Démarrage de l\'écoute de', server.name, server.url);
});
