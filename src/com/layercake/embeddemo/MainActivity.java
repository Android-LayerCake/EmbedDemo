package com.layercake.embeddemo;

import com.layercake.admobwrapper.*;
import com.layercake.facebookwrapper.*;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	public static String TAG = "LayerCake EmbedDemo";
	
	IRemoteEmbeddedAd childAdInterface;
	IRemoteFacebookPlugin childFacebookLikeInterface;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public IBinder setupBinderFacebookLike(View v) {
		return new IFacebookPluginContainer.Stub() {

			@Override
			public void registerChildInterface(IBinder childInterface)
					throws RemoteException {
				childFacebookLikeInterface = IRemoteFacebookPlugin.Stub.asInterface(childInterface);
				
				// Go ahead and actually load the plugins
				try {
					childFacebookLikeInterface.createLikePlugin("535636203166443", "http://layercake.cs.washington.edu");
				} catch (RemoteException e) {
					Log.e(TAG, "Error loading facebook comments: " + e.getMessage());
				}
			}
	        
	    };
	}
	
	public IBinder setupBinderAd(View v) {
		return new IEmbeddedAdContainer.Stub() {

			@Override
			public void onReceiveAd() throws RemoteException {
				Log.i(TAG, "Received ad!");
			}

			@Override
			public void onFailedToReceiveAd(String errorCode)
					throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPresentScreen() throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDismissScreen() throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLeaveApplication() throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void registerChildInterface(
					IBinder childInterface) throws RemoteException {
				childAdInterface = IRemoteEmbeddedAd.Stub.asInterface(childInterface);
				
				// Go ahead and actually load the ad
				try {
					childAdInterface.createAdView("BANNER", "a14f17385f00cbc");
					childAdInterface.loadAdRequest("FEMALE", null, null, null, "TEST_EMULATOR");
				} catch (RemoteException e) {
					Log.e(TAG, "Error loading ad: " + e.getMessage());
				}
			}

	        
	    };
	}
	

}