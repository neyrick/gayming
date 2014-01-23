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
	this.busy = false;
	this.dumped = false;
	this.dispoMJ = false;
	this.dispoPJ = false;
	
	this.dispo = function() { return (this.dispoMJ || this.dispoPJ); }
	this.ongame = function() { return (this.pj || this.mj); }
	this.showNotDispoPJ = function() {return !(this.dispoPJ || this.ongame() || this.busy);}
	this.showNotDispoMJ = function() {return !(this.dispoMJ || this.ongame() || this.busy); }
}

function UserStatus(username, weeks) {

// pour chaque timeframe: busy à true ou false
// pour chaque settingtf: busy à true ou false, dispoPJ à true ou false, dispoMJ à true ou false, dumped à true ou false
	this.username=username;
	this.status = new Object();

	this.updateTimeframeStatus = function(dayid, timeframe) {
		var s, setting, settingStatus, g, game, busy, hasgames;
		this.status[dayid][timeframe.code] = new Object();
		if (typeof timeframe.settings == "undefined") return;
		busy = false;
		for (s = 0; s < timeframe.settings.length; s++) {
			setting = timeframe.settings[s];
			settingStatus = new tfSettingStatus();
			this.status[dayid][timeframe.code][setting.code] = settingStatus;
			hasgames = false;
			if (typeof setting.games != "undefined") {
				for (g = 0; g < setting.games.length; g++) {
					hasgames = true;
					game = setting.games[g];
					if (game.gm.name == this.username) {
						settingStatus.mj = true;
						busy = true;
						break;
					}
					if (userIsIn(this.username, game.players)) {
						settingStatus.pj = true;
						busy = true;
						break;
					}
				}
			}
			settingStatus.dumped = (hasgames && !settingStatus.ongame());
			settingStatus.dispoPJ = userIsIn(this.username, setting.availableplayers);
			settingStatus.dispoMJ = userIsIn(this.username, setting.availablegms);
		}
		if (busy) {
			for (code in this.status[dayid][timeframe.code]) {
				settingStatus = this.status[dayid][timeframe.code][code];
				settingStatus.busy = !settingStatus.ongame();
			}
		}			
	}

	this.updateStatus = function(weeks) {
		var w, d, day, t, timeframe;
		for (w = 0; w < weeks.length; w++) {
			if (typeof weeks[w].days == "undefined") continue;
			for (d = 0; d < weeks[w].days.length; d++) {
				day = weeks[w].days[d];
				this.status[day.id] = new Object();
				if (typeof day.timeframes == "undefined") continue;
				for (t = 0; t < day.timeframes.length; t++) {
					this.updateTimeframeStatus(day.id, day.timeframes[t]);					
				}
			}
		}
	}

	this.updateStatus(weeks);

}

function isLoggedIn() {
	return (typeof angular.element('body').scope().currentUser != 'undefined');
}

function getTfSettingCode(item) {
	return item.data('dayid') + '-' + item.data('timeframe') + '-' + item.data('settingcode');
}


