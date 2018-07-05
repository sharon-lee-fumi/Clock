package pointclickcare.lish.clock.ui.Clock.Services;

import pointclickcare.lish.clock.model.Response;

public interface ResponseCallback<T extends Response> {

    void onResponse(T response);
}
