package swagger.api;

import retrofit2.Call;
import retrofit2.http.*;

import Model.Book;

public interface DetailsApi {
  
  /**
   * Get the deatails of a book by id
   * 
   * @param id 
   * @return Call<Book>
   */
  
  @GET("details/{id}")
  Call<Book> getDetails(
    @Path("id") Integer id
  );

  
  /**
   * Delete a book by id
   * 
   * @param id 
   * @return Call<Void>
   */
  
  @DELETE("details/{id}")
  Call<Void> deleteBook(
    @Path("id") Integer id
  );

  
}
