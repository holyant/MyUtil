package common.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *<p>Title: PageHelp.java </p>
 * <p>Description:
 *		分页查询帮助类，基于Oracle
 * </p>
 * <p>Copyright: Copyright (c) Asiainfo 2010</p>
 * <p>Author: Gong Jingcai</p>
 * <p>E-mail: gongjc@asiainfo.com</p>
 * <p>MSN: willbekiller@hotmail.com</p>
 * <p>Version 1.0</p>
 * <p>May 13, 2010 12:57:43 AM</p>
 * </p>
 */

@Component("page")
public class PageHelp
{
	private static Logger logger = Logger.getLogger(PageHelp.class);
	
	/**
	 * 无参构造方法
	 */
	public PageHelp(){}
	
	/**
	 * 有参构造方法
	 */
	public PageHelp(int totalRows)
	{

		this.totalRows = totalRows;
		this.totalPages = this.totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0)
		{
			this.totalPages++;
		}
		//this.currentPage = 1;
		//this.startRow = 1;
		//this.endRow = startRow+pageSize - 1;
	}
	
	/**
	 * 如果采用无参构造方法，使用前需调用此初始化方法
	 */
	public void init(int totalRows)
	{
		this.totalRows = totalRows;
		this.totalPages = this.totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0)
		{
			this.totalPages++;
		}
		//this.currentPage = 1;
		//this.startRow = 1;
		//this.endRow = startRow+pageSize - 1;
	}

	/**
	 * 执行上一页
	 */
	public void previous()
	{
		if (currentPage == 1)
		{
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize + 1;
		endRow = startRow+pageSize - 1;
		logger.debug("分页对象执行上页操作，执行后当前页：["+currentPage+"]");
	}

	/**
	 * 执行下一页
	 */
	public void next()
	{
		if (currentPage < totalPages)
		{
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize + 1; 
		endRow = startRow+pageSize - 1;
		logger.debug("分页对象执行下页操作，执行后当前页：["+currentPage+"]");
	}

	/**
	 * 执行最后一页
	 */
	public void last()
	{
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize + 1;
		endRow = startRow+pageSize - 1;
		logger.debug("分页对象执行末页操作，执行后当前页：["+currentPage+"]");
	}

	/**
	 * 执行第一页
	 */
	public void first()
	{
		currentPage = 1;
		startRow = (currentPage - 1) * pageSize + 1;
		endRow = startRow+pageSize - 1;
		logger.debug("分页对象执行首页操作，执行后当前页：["+currentPage+"]");
	}

	/**
	 * 执行指定页
	 */
	public void refresh(int thePage)
	{
		logger.debug("分页对象执行指定页操作，指定页：["+thePage+"]");
		this.currentPage = thePage;
		if (this.currentPage > totalPages)
		{
			this.last();
		}else if (this.currentPage < 1)
		{
			this.first();
		}else{
			startRow = (currentPage - 1) * pageSize + 1;
			endRow = startRow + pageSize - 1;
		}
		logger.debug("分页对象执行指定页操作，执行后当前页：["+currentPage+"]");
	}
	
	/**
	 * 执行指定页
	 */
	public void pageTurning()
	{
		if(this.pageMethod.equals("default"))
		{
			this.first();
		}else if(this.pageMethod.equals("previous"))
		{
			this.previous();
		}else if(this.pageMethod.equals("next"))
		{
			this.next();
		}else if(this.pageMethod.equals("last"))
		{
			this.last();
		}else if(this.pageMethod.equals("first"))
		{
			this.first();
		}else if(this.pageMethod.equals("refresh"))
		{
			this.refresh(thePage);
		}else
		{
			throw new RuntimeException("未知的翻页操作！");
		}
	}
	
	/**
	 * 输出分页对象信息
	 */
	public String toString()
	{
		return "分页对象信息-> 总行数:["+totalRows+"] 每页显示的行数:["+pageSize+"] 当前页:["+currentPage+"] 起始行:["+startRow+"] 结束行：["+endRow+"] 指定页:["+thePage+"]";
	}
	
	/**
	 * 总行数
	 */
	private int totalRows;
	
	/**
	 * 每页显示的行数
	 */
	private int pageSize = 20;
	
	/**
	 * 当前页号
	 */
	private int currentPage;
	
	/**
	 * 总页数
	 */
	private int totalPages;	
	
	/**
	 * 当前页在数据库中的起始行
	 */
	private int startRow;
	
	/**
	 * 当前页在数据库中的结束行
	 */
	private int endRow;
	
	/**
	 * 翻页操作标识
	 */
	private String pageMethod = "default";
	
	/**
	 * 指定页
	 */
	private int thePage;

	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public int getStartRow()
	{
		return startRow;
	}
	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}
	public int getTotalPages()
	{
		return totalPages;
	}
	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}
	public int getTotalRows()
	{
		return totalRows;
	}
	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}
	public int getEndRow()
	{
		return endRow;
	}
	public String getPageMethod()
	{
		return pageMethod;
	}
	public void setPageMethod(String pageMethod)
	{
		this.pageMethod = pageMethod;
	}
	public int getThePage()
	{
		return thePage;
	}
	public void setThePage(int thePage)
	{
		this.thePage = thePage;
	}		
}
