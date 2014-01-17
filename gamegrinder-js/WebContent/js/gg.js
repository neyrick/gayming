function isLoggedIn() {
	return (typeof angular.element('body').scope().currentUser != 'undefined');
}

function getTfSettingCode(item) {
	return item.data('dayid') + '-' + item.data('timeframe') + '-' + item.data('settingcode');
}


