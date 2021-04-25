package swagger.api;

import Model.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface EditorApi {
  
  /**
   * Update an existing book
   * 
   * @param body 
   * @return Call<Void>
   */
  
  @PUT("editor")
  Call<Book> updateBook(
    @Body Book body
  );

  
  /**
   * Create a new book
   * 
   * @param body 
   * @return Call<Void>
   */
  
  @POST("editor")
  Call<Book> postBook(
    @Body Book body
  );

  
}
