function userIsIn(username, itemlist) {
	var i;
	if (typeof itemlist == "undefined") return false;
        for (i = 0; i < itemlist.length; i++) {
            if (itemlist[i].name == username) return true;            
        }
        return false;
}

function tfSettingStatus() {
	this.pj = false;
	this.mj = false;
	this.dispoMJ = false;
	this.dispoPJ = false;

}

function isLoggedIn() {
	return (typeof angular.element('body').scope().currentUser != 'undefined');
}

function lockTooltip(scope, element) {
	element.qtip('api').set('hide.event', 'unfocus');
	element.qtip('api').set('hide.inactive', false);
	scope.tooltipLock.lock = true;
	$('#ggoverlay').addClass('active');
}

function getMyScheduleId(name, schedule, role) {
    var schedules;
    if (role == 'GM') schedules = schedule.availablegms;
    else if (role == 'PLAYER') schedules = schedule.availableplayers;
    for (var i = 0; i < schedules.length; i++) {
        if (schedules[i].name == name) {
            return schedules[i].id;
        }
    }
};

function getMyGMScheduleId(name, games) {
    for (var i = 0; i < games.length; i++) {
        if (games[i].gm.name == name) {
            return games[i].gm.id;
        }
    }
};

function getMyPlayerScheduleId(name, games) {
    var i, j, players;
    for (i = 0; i < games.length; i++) {
        players = games[i].players;
        for (j = 0; j < players.length; j++) {
            if (players[j].name == name) {
                return players[i].id;
            }
        }
    }
};
    
