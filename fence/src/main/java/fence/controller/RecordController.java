package fence.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fence.service.IRecordService;
import fence.core.annotation.LoginRequired;
import fence.core.annotation.RecordLog;
import fence.entity.PageData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import fence.entity.RecordEntity;
import java.util.List;

@RestController
@Api(tags = "record接口")
@RequestMapping("/record")
public class RecordController {


	private IRecordService service;

	@Autowired
	public RecordController(IRecordService service) {
		this.service = service;
	}
	
	/**
	 * 查询
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/select")
	public List<RecordEntity> select(@RequestBody RecordEntity entity) {
		return service.select(entity);
	}

	/**
	 * 模糊查询
	 *
	 * @return
	 */
	@ApiOperation(value = "模糊查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/likeSelect")
	public PageData<RecordEntity> likeSelect(@RequestBody RecordEntity entity) {
		return service.likeSelect(entity);
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@ApiOperation(value = "更新")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/update")
	public void update(@RequestBody RecordEntity entity) {
		service.update(entity);
	}

	/**
	 * 添加
	 *
	 * @return
	 */
	@ApiOperation(value = "添加")
    @LoginRequired
	@RecordLog
	@PostMapping(value = "/add")
	public void add(@RequestBody RecordEntity entity) {
		service.add(entity);
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/delete")
	public void delete(@RequestBody RecordEntity entity) {
		service.delete(entity);
	}


	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/remove")
	public void deleteByList(@RequestBody String input) {
		String output = input.substring(input.indexOf("[") + 1 ,input.indexOf("]"));
		String[] idstr = output.split(",");
		RecordEntity ue =new RecordEntity();
		for (String id : idstr) {
			ue.setId(Integer.parseInt(id));
			service.delete(ue);
		}
	}
	/**
	 * 导出excel
	 *
	 * @return
	 */
	@ApiOperation(value = "导出excel")
	@LoginRequired
	@RecordLog
	@GetMapping("/exportExcel")
	public void exportExcel(RecordEntity entity, HttpServletResponse response) {
		service.exportExcel(entity, response);
	}

}
