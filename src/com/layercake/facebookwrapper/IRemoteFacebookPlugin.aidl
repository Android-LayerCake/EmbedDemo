package com.layercake.facebookwrapper;

interface IRemoteFacebookPlugin {

	/* Create comments plugin */
	void createCommentsPlugin(String appId, String url);
	
	/* Create like plugin */
	void createLikePlugin(String appId, String url);

}