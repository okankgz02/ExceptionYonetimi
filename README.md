Exception testlerini yakalamak,fırlatılan exceptionları ele alır
Örneğin 

  if (foo == null || foo.getId() == null) {
            throw new Exception();
        }
fırlatılan bu Exception hatasını nasıl teste yakalarız

  @Test(expected = Exception.class)
    public void updateFoo_exception() throws Exception {
        Foo foo=null;
        myService.updateFoo(foo);
    }

Test claslarında yakalama biçini
