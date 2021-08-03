package Values;

import static org.junit.jupiter.api.Assertions.assertTrue;

import Values.VavrTry.ClientException;
import Values.VavrTry.Response;
import io.vavr.control.Try;

public class VavrTry {
    
    /**
     * Similar to `Either`
     * Instances of `Try` are either an instance of `Success` or `Failure`
    */
    
    // Functional way of error handling other than standard try-catch block
    private HttpClient httpClient;
    
    public Response getResponse() {
        try {
            return httpClient.call();
        } catch (ClientException exception) { return null; }
    } // this really disrupts program flow

    // functional way of dealing with try-catch
    public Try<Response> getResponseFunctional() {
        return Try.of(httpClient::call);
    }
    
    public static void main(String[] args) {
        String id = "a";
        HttpClient httpClient = () -> new Response(id);

        Try<Response> response = new VavrTry().getResponseFunctional();
        Integer chainedResult = response.map(s -> s.id.hashCode()).getOrElse(1);

        assertTrue(response.isSuccess());
    }
   
    static class Response { 
        final String id;

        Response(String id) { this.id = id; }
    }
    static class ClientException extends Exception { }

    static interface HttpClient {
        Response call() throws ClientException;
    }
}


