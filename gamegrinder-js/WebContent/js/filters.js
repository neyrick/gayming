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
  
  
  function userIsInNameList(username, itemlist) {
      if (typeof itemlist == "undefined") return false;
      return (itemlist.indexOf(username) != -1);
  }
  
  function userIsInGame(username, settingtf) {
        if (typeof settingtf.games == "undefined") return false;
	return (userIsGMing(username, settingtf) || userIsPlaying(username, settingtf));
  }


  function userIsGMing(username, settingtf) {
        var i,game;
        if (typeof settingtf.games == "undefined") return false;
        for (i = 0; i < settingtf.games.length; i++) {
            game = settingtf.games[i];
            if (game.gm.name == username) return true;            
        }
        return false;
  }
    
  function userIsPlaying(username, settingtf) {
        var i,game;
        if (typeof settingtf.games == "undefined") return false;
        for (i = 0; i < settingtf.games.length; i++) {
            game = settingtf.games[i];
	    if (userIsIn(username, game.players)) return true;
        }
        return false;
  }

  function userIsBusy(username, timeframe) {
        var t, settingtf;
        if (typeof timeframe.settings == "undefined") return false;
	for (t = 0; t < timeframe.settings.length; t++) {
		settingtf = timeframe.settings[t];
		if (typeof settingtf.games == "undefined") continue;
		if (userIsGMing(username, settingtf)) return true;
		if (userIsPlaying(username, settingtf)) return true;
	}
        return false;
  }

  function userIsInOtherSetting(username, currentsettingtf, timeframe) {
        var i,j,t, settingtf, game;
        if (typeof timeframe.settings == "undefined") return false;
	for (t = 0; t < timeframe.settings.length; t++) {
		settingtf = timeframe.settings[t];
		if (typeof settingtf.games == "undefined") continue;
		for (i = 0; i < settingtf.games.length; i++) {
			if (settingtf.code == currentsettingtf.code) continue;
		    	game = settingtf.games[i];
		    	if (game.gm.name == username) return true;
			if (userIsIn(username, game.players)) return true;
		}
	}
        return false;
  }
    
function userCanPlay(username, settingtf) {
	return (userIsIn(settingtf.availableplayers));
}

function userCanGM(username, settingtf) {
	return (userIsIn(settingtf.availablegms));
}

function isAvailable(username, settingtf) {
	return (userCanPlay(username, settingtf) || userCanGM(username, settingtf));
}

gamegrinderApp.filter('settingBadgeStyle', function() {
    return function(settingtf, tfSettingStatus, settingInfo, invisibleOpen, visibleClosed) {
	    if (settingInfo.open) {
	        if (invisibleOpen.indexOf('' + settingInfo.id) != -1) return 'collapsed';
	    }
	    else {
	        if (visibleClosed.indexOf('' + settingInfo.id) == -1) return 'collapsed';
	    }

	    var style = 'badge';
	    if (tfSettingStatus.ongame())	style = style + '  playBadge';
	    else if (tfSettingStatus.dumped) style = style + '  noPlayBadge';
	    else if (tfSettingStatus.dispoPJ || tfSettingStatus.dispoMJ) style = style + '  availableBadge';
	    else style = style + '  notAvailableBadge';
	    return style;
    };
});

gamegrinderApp.filter('dateFromId', function() {
    return function(dayid) {
        if (typeof dayid == "undefined") return '';
        var year = dayid.substring(0, 4);
        var month = dayid.substring(4, 6);
        var day = dayid.substring(6, 8);
        var resdate = new Date(year, month, day, 0, 0, 0, 0);
        return resdate.toLocaleDateString();
    };
});

gamegrinderApp.filter('settingVisibleStyle', function() {
    return function(settingid, defaultShow, toggledlist) {
            var show = defaultShow;
	    if (toggledlist.indexOf('' + settingid) != -1) show = !defaultShow;
	    if (show) return "ggEnabledItem";
	    else return "ggDisabledItem";
    };
});
/*
gamegrinderApp.filter('recruitPlayersStyle', function() {
    return function(player, playerlist) {
	    for (var key in playerlist) {
		    if (key == player.name) return "btn-success";
	    }
	    return "btn-default";
    };
});
*/
