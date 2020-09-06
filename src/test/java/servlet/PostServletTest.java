package servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.model.Post;
import ru.job4j.servlet.PostServlet;
import ru.job4j.store.MemStore;
import ru.job4j.store.PsqlStore;
import ru.job4j.store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PsqlStore.class})

public class PostServletTest {
    @Test
    public void doPostTest() throws ServletException, IOException {
        Store store = MemStore.instOf();
        mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("Java");
        new PostServlet().doPost(req, res);
        List<Post> expected = new ArrayList<>(store.findAllPosts());
        assertThat(expected.get(expected.size() - 1).getName(), is("Java"));
    }

    @Test
    public void doPostUpdateTest() throws ServletException, IOException {
        Store store = MemStore.instOf();
        Post post = new Post(4, "test");
        store.savePost(post);
        mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(post.getId()));
        when(req.getParameter("name")).thenReturn("Java");
        new PostServlet().doPost(req, resp);
        assertThat(store.findByIdPost(post.getId()).getName(), is("Java"));
    }
}
