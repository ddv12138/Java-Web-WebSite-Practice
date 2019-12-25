package WebComponent.Service.ServicesImpl;

import ORM.Mapper.RoleMapper;
import ORM.POJO.Role;
import WebComponent.Service.Services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	RoleMapper mapper;

	public RoleServiceImpl(RoleMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Role> listRole() {
		return mapper.listRole();
	}
}
