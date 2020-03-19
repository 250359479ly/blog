package com.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.bean.Article;
import com.blog.bean.Comment;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class ArticleController {

    @Autowired
    private	ArticleService articleService;
    @Autowired
    private CommentService commentService;

    /**
     * 文章浏览界面信息处理
     * 
     * @param request
     * @return
     */
    @RequestMapping("/article")
    public ModelAndView detail(HttpServletRequest request){

        int id=Integer.parseInt(request.getParameter("id"));
        //获取留言信息
        List<Comment> comments=commentService.allComments(id,0,10);

        //当前阅读的文章 上一篇 下一篇
        Article article=articleService.selectById(id);
        Article lastArticle=articleService.selectLastArticle(id);
        Article nextArticle=articleService.selectNextArticle(id);

        //点击次数（被阅读的次数）加一
        Integer clickNum=article.getClick();
        article.setClick(++clickNum);
        articleService.updateArticle(article);
        
        ModelAndView modelAndView=new ModelAndView("detail");
        modelAndView.addObject("article",article);
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("lastArticle",lastArticle);
        modelAndView.addObject("nextArticle",nextArticle);
        return modelAndView;
    }
    /**
     * 管理界面的文章详情浏览
     * @param request
     * @return
     */
    @RequestMapping("/admin/article/detail")
    public ModelAndView adminArticleDetail(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        Article article=articleService.selectById(id);

        ModelAndView modelAndView=new ModelAndView("/admin/article_detail");
        modelAndView.addObject("article",article);

        return modelAndView;
    }
    /**
     * 想应文章所有留言查看
     * @param request
     * @return
     */
    @RequestMapping("/admin/article/comment")
    public ModelAndView adminArticleComment(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        List<Comment> comments=commentService.allComments(id,0,10);
        ModelAndView modelAndView=new ModelAndView("/admin/comment_list");
        modelAndView.addObject("comments",comments);
        return modelAndView;
    }
    
    /**
     * 文章管理页面信息处理
     * 查询对应的文章数据
     * 
     * @param page （页码） 默认=1
     * @param pageSize（分页大小）  默认=10
     * @return
     */
    @RequestMapping("/admin/article/list")
    public ModelAndView articleList(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="10") Integer pageSize){
        PageHelper.startPage(page, pageSize);
        List<Article> articles=articleService.queryAll();
        PageInfo<Article> pageInfo=new PageInfo<Article>(articles);
        ModelAndView modelAndView=new ModelAndView("/admin/article_list");
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
    /**
     * 写文章界面
     * @return
     */
    @RequestMapping("/admin/article/add")
    public ModelAndView articleAdd(){
        ModelAndView modelAndView=new ModelAndView("/admin/article_add");
        return modelAndView;
    }
    
    /**
     * 执行新增文章命令
     * 
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/admin/article/add/do")
    public String articleAddDo(HttpServletRequest request,RedirectAttributes redirectAttributes){
        Article article=new Article();
        article.setTitle(request.getParameter("title"));
        article.setCatalogId(Integer.parseInt(request.getParameter("catalogId")));
        article.setKeywords(request.getParameter("keywords"));
        article.setdesci(request.getParameter("desci"));
        article.setContent(request.getParameter("content"));
        article.setTime(new Date());
        if (articleService.insert(article)){
            redirectAttributes.addFlashAttribute("succ", "发表文章成功！");
            return "redirect:/admin/article/list";
        }else {
            redirectAttributes.addFlashAttribute("error", "发表文章失败！");
            return "redirect:/admin/article/add";
        }
    }
    /**
     * 根据文章标题或内容进行模糊查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/article/search")
    public ModelAndView articleSearch(HttpServletRequest request){
        String word=request.getParameter("word");
        List<Article> articles=articleService.selectByWord(word);

        ModelAndView modelAndView=new ModelAndView("/admin/article_list");
        modelAndView.addObject("articles",articles);
        return modelAndView;
    }
    /**
     * 文章编辑界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/article/edit")
    public ModelAndView articleEdit(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        Article article=articleService.selectById(id);
        ModelAndView modelAndView=new ModelAndView("/admin/article_edit");
        modelAndView.addObject("article",article);
        return modelAndView;
    }
    /**
     * 执行文章修改操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/article/edit/do")
    public ModelAndView articleEditDo(HttpServletRequest request){
        Article article=new Article();
        article.setId(Integer.parseInt(request.getParameter("id")));
        article.setTitle(request.getParameter("title"));
        article.setCatalogId(Integer.parseInt(request.getParameter("catalogId")));
        article.setKeywords(request.getParameter("keywords"));
        article.setdesci(request.getParameter("desci"));
        article.setContent(request.getParameter("content"));
        ModelAndView modelAndView=new ModelAndView("/admin/article_edit");
        if (articleService.updateArticle(article)){
            modelAndView.addObject("succ", "修改文章成功！");

        }else {
            modelAndView.addObject("error", "修改文章失败！");
        }
        modelAndView.addObject("article",article);
        return modelAndView;
    }
    
    /**
     * 删除对应的文章信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/article/del", method = RequestMethod.POST)
    public  Object loginCheck(HttpServletRequest request) {
        int id=Integer.parseInt(request.getParameter("id"));
        int result=articleService.deleteById(id);
        HashMap<String, String> res = new HashMap<String, String>();
        if (result==1){
            res.put("stateCode", "1");
        }else {
            res.put("stateCode", "0");
        }
        return res;
    }
}
