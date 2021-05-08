package swagger.api;

import java.util.List;

import Model.Booklet;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LibraryApi {
  
  /**
   * Get all booklets matching the querry parameters
   * 
   * @param title 
   * @param author 
   * @return Call<List<Booklet>>
   */
  
  @GET("library")
  Call<List<Booklet>> getBooklets(
    @Query("title") String title, @Query("author") String author
  );

  
  /**
   * Delete a book by Id
   * 
   * @param id 
   * @return Call<Void>
   */
  
  @DELETE("library/{Id}")
  Call<Void> deleteBooklet(
    @Path("Id") Integer id
  );

  
}
