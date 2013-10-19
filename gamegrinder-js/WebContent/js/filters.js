'use strict';
/*
var dowCode = [ "", "LU", "MA", "ME", "JE", "VE", "SA", "DI"];

gamegrinderApp.filter('dowCode', function() {
    return function(input) {
//	return dowCode[input];
	    return "LU";
    };
});
*/

function isAvailable(settingtf, username) {
	    var i, player;
	    if (typeof settingtf.availablegms != 'undefined') {
		    for (i = 0; i < settingtf.availablegms.length; i++) {
			player = settingtf.availablegms[i];
			if (player.name == username) return true;
		    }
	    }
	    if (typeof settingtf.availableplayers != 'undefined') {
		    for (i = 0; i < settingtf.availableplayers.length; i++) {
			player = settingtf.availableplayers[i];
			if (player.name == username) return true;
		    }
	    }
	    return false;
}

function isPlaying(settingtf, username) {
	    var i, j;
	    var game, player;
	    if (typeof settingtf.games != 'undefined') {
		    for (i = 0; i < settingtf.games.length; i++) {
			game = settingtf.games[i];
			if (game.gm == username) return true;
			    for (j = 0; j < game.players.length; j++) {
				player = game.players[j];
				if (player.name == username) return true;
			    }
		    }
	    }
	    return false;
}

gamegrinderApp.filter('settingBadgeStyle', function() {
    return function(settingtf, username) {
	    var hasGame = ((typeof settingtf.games != 'undefined') && (settingtf.games.length > 0));
	    var hasGM = ((typeof settingtf.availablegms != 'undefined') && (settingtf.availablegms.length > 0));
	    var style = 'badge';
	    if (hasGame) {
		    if (isPlaying(settingtf, username))
			style = style + '  withMyPABadge withMyGameBadge';
		    else if (isAvailable(settingtf, username))
			style = style + '  withMyPABadge withGameBadge';
		    else
			style = style + '  withGameBadge';
	    }
	    else {
		    if (isAvailable(settingtf, username))
			style = style + '  withMyPABadge';
		    if (hasGM)
			style = style + '  withGmBadge';
		    else
			style = style + '  withoutGmBadge';
	    }	    
	    return style;
    };
});

gamegrinderApp.filter('recruitPlayersStyle', function() {
    return function(player, playerlist) {
	    for (var key in playerlist) {
		    if (key == player.name) return "btn-success";
	    }
	    return "btn-default";
    };
});