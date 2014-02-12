var path           = require('path')
  , templatesDir   = path.join(__dirname, '..', 'templates')
  , emailTemplates = require('email-templates')
  , services = require("./services")
  , nodemailer = require('nodemailer');


var ejs = require('ejs');
ejs.open = '{{';
ejs.close = '}}';

var timeframeNames = { AFTERNOON : "après-midi", EVENING : "soir" };

var appFrom = 'Rêves et Légendes <blabla@prout.com>';

var transport = nodemailer.createTransport("SMTP", {
/* a configurer */
//      host: "",  /* ou alors... */
      service: "Gmail",
      auth: {
        user: "neyrick@gmail.com",
        pass: ""
      }
    });

function parseDayId(dayid) {
	var daydate = new Date(dayid.substr(0, 4), dayid.substr(4, 2),  dayid.substr(6, 2), 0, 0, 0, 0);
	return daydate.toLocaleDateString();
}

// TODO: afficher le comment de chaque player

var msgBuilders = {
	"ADD_GAME": function(eventData) {
			var recipient, results = [];
			for (var i = -1; i < eventData.data.players; i++) {
				if (i == -1) recipient = { name : eventData.player };
				else recipient = { name : eventData.data.players[i].name };
				results.push( {
					template: 'add_game',
					recipient: recipient,
					subject: 'Validation d\'une partie de ' + eventData.setting.name,
					setting: eventData.setting,
					date: parseDayId(eventData.dayid),
					timeframe: timeframeNames[eventData.timeframe],
					gm: eventData.player,
					players: eventData.data.players
				});
			}
			return results;
		},
	"RFM_GAME": function(eventData) {			
			return [{
				template: 'rfm_game',
				recipient: { name : '', address : '' },
				subject: '',
			}];
		},
	"DRP_GAME": function(eventData) {			
			return [{
				template: 'drp_game',
				recipient: { name : '', address : '' },
				subject: '',
			}];
		},
	"DEL_GAME": function(eventData) {			
			return [{
				template: 'del_game',
				recipient: { name : '', address : '' },
				subject: '',
			}];
		},
	"SET_COMMENT": function(eventData) {			
			return [{
				template: 'set_comment',
				recipient: { name : '', address : '' },
				subject: '',
			}];
		},
};

function processMessages(builder, eventData, msgHandler) {
	
	var idsetting = eventData.setting;

	services.fetchSettingById(eventData.setting, function(err, setting) {
		if (err) {
			console.log("Impossible d'identifier la chronique " + eventData.setting);
			return;
		}
		eventData.setting = setting;
		var msgDataArray = builder(eventData);
		var recipients = [];
		msgDataArray.forEach(function (msgData) {
			recipients.push(msgData.recipient.name);
		});

		services.fetchPlayerData(recipients, function (err, players) {
			if (err) {
				console.log("Impossible de trouver les infos sur " + recipients);
				return;
			}
			var emails = {};
			players.forEach(function (player) { emails[player.name] = player.email; });
			
			msgDataArray.forEach(function (msgData) {
				msgData.recipient.address = emails[msgData.recipient.name];;
				msgHandler(msgData);
			});

		});

	}); 

}


exports.notify = function (eventData, successCallback, errorCallback) {

	var builder, action = eventData['action'];
	if (typeof action == "undefined") {
		errorCallback("Action non définie");
		return;
	}
	builder = msgBuilders[action];

	// Pas de message pour cette action
	if (typeof handler == "undefined") return;
	
	processMessages(builder,eventData, function (msgData) {

	    template(msgData.template, msgData, function(err, html, text) {
	      if (err) {
		errorCallback(err);
	      } else {
		transport.sendMail({
		  from: appFrom,
		  to: msgData.recipient.address,
		  subject: msgData.subject,
		  html: html,
		  text: text
		}, function(err, response) {
		  if (err) {
		    errorCallback(err.message);
		  } else {
		    successCallback(msgData);
		  }
		});
	      }
	    });
	}, errorCallback);
}
