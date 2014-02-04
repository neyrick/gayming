var actionParsers = {};

actionParsers['ADD_DISPO'] = function(result, body, params) {
	result.dayid = body.dayid;
	result.timeframe = body.timeframe;
	result.player = body.player;
	result.setting = body.setting;
	result.data = { role : body.role };
	return result;
}

actionParsers['DEL_DISPO'] = function(result, body, params) {	
	result.dayid = body.dayid;
	result.timeframe = body.timeframe;
	result.player = body.player;
	result.setting = body.setting;
	result.data = { role : body.role };
	return result;
}

    exports.buildHistoryEntry = function(req, parseBody) {
	var reqaction = req.params['log_action'];
	if (typeof reqaction != "undefined") {
		var parser = actionParsers[reqaction];
		var result = { action : reqaction, address : req.connection.remoteAddress}
		var body = req.body;

		if (parseBody) body = JSON.parse(body);
		if (typeof parser != "undefined") {
			return parser(result, body, req.params);
		}
		else {
			console.log("Action inconnue: " + reqaction);
			return undefined;
		}
	}
	return undefined;
    }


