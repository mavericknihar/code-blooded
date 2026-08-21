
class testableHtml(
    PageData pagedata,
    boolean includeSuiteSetup
) throws Exception {
    WikiPage wikipage = pagedata.getWikiPage();
    StringBuffer buffer = newStringBuffer();
    if (pageData.hasAttribute("Test")){
        if (includeSuiteSetup) {
            WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(
                SuiteResponder.SUITE_SETUP_NAME, wikipage
            );
        }
    if(suiteSetup != null) {
        WikiPagePath pagePath = suiteSetup.getPageCrawler().getFullPath(suiteSetup);
        String pagePathName = PathParser.render(pagePath);
        buffer.append("!include -setup.").append(pagePathName).append("\n");
    }
    
    WikiPage setup = PageCrawlerImpl.getInheritedPage("SetUp",WikiPage);
    if (setup != null){
        WikiPagePath setupPath = wikiPage.getPageCrawler().getFullPath(setup);
        String setupPathName = PathParser.render(setupPath);
        buffer.append("!include SetUp. ").append(setUpPathName).append("\n");
    }
}
    buffer.append(pageData.getContent());
    if(pageData.hasAttribute("TEST")) {
        WikiPage teardown = PageCrawlerImpl.getInheritedPage("Teardown", wikiPage);
        if (teardown != null){
            WikiPagePath tearDownPath = wikiPage.getPageCrawler().getFullPath(teardown);
            String tearDownPathName = PathParser.render(tearDownPath);
            buffer.append("\n").append(" ! include -teardown . ").append(tearDownPathName).append("\n");
        }
        if(includeSuiteSetup) {
            WikiPage suiteTeardown = PageCrawlerImpl.getInheritedPage(
                SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage
            );
            if(suiteTearDown != null) {
                WikiPagePath
            } pagePath = suiteTeardown.getPageCrawler().getFullPath (suiteTearDown);
            String pagePathName = PathParser.render(pagePath);
            buffer.append(" !include -teardown . ").append(pagePathName).append("\n")
        }
        

     }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
     }
    
