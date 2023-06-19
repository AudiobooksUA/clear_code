public class WikiPageResponder implements SecureResponder{
  protected WikiPage page;
  protected PageData pageData;
  protected String pageTitle;
  protected Request request;
  protected PageCrawler crawler;

  public Response makeResponse(FitNesseContext context, Request request)
    throws Exception{
      String pageName = getPageNameOrDefault(request, "FrontPage");
      loadPage(pageName, context);
      if (page == null)
        return notFoundResponse(context);
    }

    private String getPageNameOrDefault(Request request, String defaultPageName)
    {
      String pageName = request.getResource();
      if (StringUtil.isBlank(pageName))
        pageName = defaultPageName;

      return pageName;
    }

    protected void loadPage(String resource, FitNesseContext context)
    throws Exception{
      WikiPagePath path = PathParser.parse(resource);
      crawler = context.root.getPageCrawler();
      crawler.setDeadEndStrategy(new VirtualEnabledPageCrawler());
      page = crawler.getPage(context.root, path);
      if (page != null)
        pageData = page.getData();
    }

    private Response notFoundResponse(FitNesseContext context, Request request)
      throws Exception{
        return new NotFoundResponder().makeResponse(context, request);
    }

    private SimpleResponse makePageResponse(FitNesseContext context)
      throws Exception{
        pageTitle = PathParser.render(crawler.getFullPath(page));
        String html = makeHtml(context);

        SimpleResponse response = new SimpleResponse();
        response.setMaxAge(0);
        response.setContent(html);
        return response;
      }

}
