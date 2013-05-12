package com.korotovsky.client.events;

import com.korotovsky.client.network.*;

public interface PlayerEvents {
    public void onPlayerConnected(Request request);

    public void onPlayerDisconnected(Request request);

    public void onGameStarted(Request request);

    public void onGameEnded(Request request);

    public void onWinnerReceived(Request request);

    public void onSuccessfulHandshake(Request request);
}
