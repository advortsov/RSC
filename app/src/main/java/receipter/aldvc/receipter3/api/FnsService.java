package receipter.aldvc.receipter3.api;

import java.util.Map;

import receipter.aldvc.receipter3.content.dto.DocumentResponse;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface FnsService {

    @GET("/v1/inns/*/kkts/*/fss/{fn}/tickets/{fd}")
    Observable<DocumentResponse> receipt(
            @HeaderMap Map<String, String> headers,
            @Path("fn") String fn,
            @Path("fd") String fd,
            @Query("fiscalSign") String fs,
            @Query("sendToEmail") String sendToEmail
    );

}
