package swagger.api;

import retrofit2.Call;
import retrofit2.http.*;

import Model.Book;

public interface DetailsApi {
  
  /**
   * Get the deatails of a book by Id
   * 
   * @param id 
   * @return Call<Book>
   */
  
  @GET("details/{Id}")
  Call<Book> getDetails(
    @Path("Id") Integer id
  );

  
  /**
   * Delete a book by Id
   * 
   * @param id 
   * @return Call<Void>
   */
  
  @DELETE("details/{Id}")
  Call<Void> deleteBook(
    @Path("Id") Integer id
  );

  
}
