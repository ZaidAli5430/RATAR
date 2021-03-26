package zaid_ali.example.ratar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserTokenApi {
    @GET("/rtcToken")
    Call<UserAuthenticationToken> getToken(@Query("ChannelName") String ChannelName);
}
