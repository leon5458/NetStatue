package com.hly.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class NetStatue {
    public static boolean isConnected(Context context) {
        if (null == context) {
            return false;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network[] networks = new Network[0];
            if (connectivity != null) {
                networks = connectivity.getAllNetworks();
            }
            for (Network network : networks) {
                NetworkCapabilities capabilities = connectivity.getNetworkCapabilities(network);
//                if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))) {
//                    return true;
//                }
                if (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    return true;
                }
            }
        } else {
            NetworkInfo info = null;
            if (connectivity != null) {
                info = connectivity.getActiveNetworkInfo();
            }
            if (info != null && info.isAvailable()) {
                return true;
            }

        }
        return false;
    }


    public static boolean isWifi(Context context) {
        if (null == context) {
            return false;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivity != null) {
                Network networks = connectivity.getActiveNetwork();
                NetworkCapabilities networkCapabilities = connectivity.getNetworkCapabilities(networks);
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    }
                }
            }
        } else {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    return true;
                }
            }

        }

        return false;
    }


    public static boolean isMobile(Context context) {
        if (null == context) {
            return false;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivity != null) {
                Network networks = connectivity.getActiveNetwork();
                NetworkCapabilities networkCapabilities = connectivity.getNetworkCapabilities(networks);
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    }
                }
            }
        } else {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return true;
                }
            }

        }

        return false;
    }
}
