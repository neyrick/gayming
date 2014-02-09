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

