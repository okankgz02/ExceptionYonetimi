import domain.Foo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MyRepositoryDAO;
import service.MyServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyServiceImplTest {

    @InjectMocks
    private MyServiceImpl myService;

    @Mock
    private MyRepositoryDAO myRepositoryDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() throws Exception {

        final Date date = new Date();

        final List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        final Foo foo = new Foo();
        foo.setId(1);
        foo.setLastModifiedDate(date);
        foo.setName("name");
        foo.setList(list);

        when(myRepositoryDAO.getById(Mockito.eq(1))).thenReturn(foo);

        final Foo result = myService.getById(1);

        Assert.assertEquals(Integer.valueOf(1), result.getId());
        Assert.assertEquals(date, result.getLastModifiedDate());
        Assert.assertEquals("name", result.getName());
        Assert.assertEquals(Integer.valueOf(3), Integer.valueOf(result.getList().size()));

        // two lists are defined to be equal if they contain the same elements in the same order.
        Assert.assertEquals(list, result.getList());
    }

    @Test(expected = Exception.class)
    public void getById_nullId() throws Exception {
        myService.getById(null);
    }

    @Test(expected = Exception.class)
    public void getById_daoReturnNull() throws Exception {

        when(myRepositoryDAO.getById(Mockito.eq(1))).thenReturn(null);

        myService.getById(1);
    }

    @Test(expected = Exception.class)
    public void findByName() throws Exception {
        myService.findByName(null);
    }
    @Test
    public void findByNameTrue() throws Exception {
        myService.findByName("okan");
        when(myRepositoryDAO.findByName("okan")).thenReturn(new ArrayList<Foo>());

    }
    @Test
    public void findByNamefooException() throws Exception {
        List<Foo> foo=new ArrayList<Foo>();
        myService.findByName("okan");
        when(myRepositoryDAO.findByName("okan")).thenReturn(foo);

        List<Foo>  expected=myRepositoryDAO.findByName("okan");
        assertSame(foo,expected);
    }


    @Test
    public void updateFoo() throws Exception {

    }
}