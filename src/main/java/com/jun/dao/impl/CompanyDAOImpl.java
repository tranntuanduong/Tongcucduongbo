package com.jun.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.jun.dao.ICompanyDAO;
import com.jun.mapper.CompanyMapper;
import com.jun.model.Company;

public class CompanyDAOImpl extends AbstractDAO<Company> implements ICompanyDAO{
	private static final Logger log = Logger.getLogger(CompanyDAOImpl.class);
	
	@Override
	public Long update(Company transportUnit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Company findByName(String name) {
		String sql = "SELECT * FROM company WHERE name = ?";
		List<Company> transportUnits = findByProperty(sql, new CompanyMapper(), name);
		log.info(sql);
		return transportUnits.isEmpty() ? null : transportUnits.get(0);
	}

	@Override
	public Integer save(Company company) {
		String sql = "INSERT INTO company(name, phone_number, taxt_number, location_id) VALUES(?, ?, ?, ?)";
		log.info(sql);
		return save(sql, company.getName(), company.getPhoneNumber(), 
				company.getTaxNumber(), company.getLocationId()).intValue();
	}

	@Override
	public Company findById(Long id) {
		String sql = "SELECT * FROM company WHERE id = ?";
		List<Company> company = findByProperty(sql, new CompanyMapper(), id);
		log.info(sql);
		return company.isEmpty() ? null : company.get(0);
	}

//	@Override
//	public Long update(Company company) {
//		String sql = "UPDATE company SET name=?, phone_number=?, taxt_code=?, provincial_id=?, code=? WHERE id=?";
//		log.info(sql);
//		return save(sql, company.getName(), company.getPhoneNumber(), 
//				company.getTaxCode(), company.getProvincialId(), company.getCode(), company.getId());
//	}

	@Override
	public List<Company> findByCode(String name) {
		String sql = "SELECT * FROM company WHERE code = ?";
		List<Company> company = findByProperty(sql, new CompanyMapper(), name);
		log.info(sql);
		return company;
	}

	@Override
	public void saveList(List<Company> companyList) {
		StringBuilder sql = new StringBuilder("INSERT INTO company(name, phone_number, taxt_number, location_id) VALUES");
		int count = 0;
		StringBuilder values = new StringBuilder("");
		for(Company company : companyList) {
			if(values.length() > 0) {
				values.append(",");
			}
			String phoneNumber = company.getPhoneNumber();
			if(phoneNumber == null) {
				phoneNumber = " ";
			}
			values.append("('"+company.getName()+"','"+phoneNumber+"','"
			+company.getTaxNumber()+"',"+company.getLocationId() +")");
			count++;
			if(count >= 2000) {
				sql.append(values);
				saveList(sql.toString());
				sql.setLength(0);
				values.setLength(0);
				sql.append("INSERT INTO company(name, phone_number, taxt_number, location_id) VALUES");
				log.info("INSERT " + count + " Object Company (IN FOR)");
				count = 0;
			}
		}
		sql.append(values);
		log.info("INSERT " + count + " Object Company");
		saveList(sql.toString());
	}

	@Override
	public HashMap<String, String> findAllNameAndId(HashMap<String, String> companyRepeat, int locationId) {
		String sql = "SELECT name, id, location_id, taxt_number FROM company WHERE location_id = ?";
		return findAllNameAndId(sql, companyRepeat, locationId);
	}
	public HashMap<String, String> findAllNameRepeat(int locationId) {
		//xem lai cho nay ???
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append("(SELECT name, count(1), GROUP_CONCAT(id) AS 'id' FROM company WHERE location_id = ? ");
		sql.append(" GROUP BY name HAVING COUNT(1) > 1) a");
		return findAllStringByProperty(sql.toString(), locationId );
	}

	@Override
	public 	HashMap<String, String> findAllNameAndTaxNumber() {
		String sql = "SELECT name, taxt_number FROM company";
		return findAllNameAndTaxNumber(sql);
	}
}
