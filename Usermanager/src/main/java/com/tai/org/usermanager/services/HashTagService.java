package com.tai.org.usermanager.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tai.org.usermanager.dto.HashTagDTO;
import com.tai.org.usermanager.enties.HashTagEntity;
import com.tai.org.usermanager.projections.TagUserTotal;
import com.tai.org.usermanager.repositories.HashTagRepository;
import com.tai.org.usermanager.repositories.UserHashTagRepository;

@Service("hashTagService")
public class HashTagService implements IHashTagService {

	@Autowired
	private HashTagRepository hashTagRepository;

	@Autowired
	private UserHashTagRepository userHashTagRepository;

	@Override
	public List<HashTagDTO> findAll() {
		// TODO Auto-generated method stub
		List<HashTagEntity> hashTags = hashTagRepository.findAll();
		List<HashTagDTO> hashTagResponses = new ArrayList<>();
		for (HashTagEntity hashTag : hashTags) {
			HashTagDTO hashTagDTO = new HashTagDTO();
			BeanUtils.copyProperties(hashTag, hashTagDTO);
			// String mysz2 = mysz.replaceAll("\\s","");
			hashTagDTO.setName2(hashTag.getName().replaceAll("\\s", ""));
			hashTagResponses.add(hashTagDTO);
		}
		return hashTagResponses;
	}

	@Override
	public HashTagDTO addHashTag(HashTagDTO hashTagDTO) {
		HashTagEntity hashtagEntity = new HashTagEntity();
		BeanUtils.copyProperties(hashTagDTO, hashtagEntity);
		HashTagEntity savedHashTag = hashTagRepository.save(hashtagEntity);
		BeanUtils.copyProperties(savedHashTag, hashTagDTO);
		return hashTagDTO;
	}

	@Override
	public boolean deleteHashTag(int id) {
		Optional<HashTagEntity> hashTagOptional = hashTagRepository.findById(id);
		if (hashTagOptional.isPresent()) {
			hashTagRepository.delete(hashTagOptional.get());
			return true;
		}
		return false;
	}

	@Override
	public Set<HashTagEntity> findByHashTagIdIn(Set<Integer> ids) {
		return hashTagRepository.findByIdIn(ids);
	}

	@Override
	public String findById(int id) {
		Optional<HashTagEntity> hashTagOpitional = hashTagRepository.findById(id);
		if (hashTagOpitional.isPresent()) {
			return hashTagOpitional.get().getName();
		}
		return null;
	}

	@Override
	public HashTagDTO countUser(int hashTagId) {

		long responseNumber = userHashTagRepository.countUser(hashTagId);
		Optional<HashTagEntity> optionalHashTag = hashTagRepository.findById(hashTagId);
		if (optionalHashTag.isPresent()) {
			HashTagDTO responseHashTag = new HashTagDTO();
			BeanUtils.copyProperties(optionalHashTag.get(), responseHashTag);
			responseHashTag.setNumberOfUser(responseNumber);
			responseHashTag.setName2(responseHashTag.getName().replaceAll("\\s", ""));
			return responseHashTag;
		}
		return null;
	}

	@Override
	public List<HashTagDTO> countAllUser() {
		List<HashTagEntity> hashTagEntities = hashTagRepository.findAll();
		List<HashTagDTO> responseHashTagDTOs = new ArrayList<>();

		// List<Integer> hashTagIds = hashTagEntities.stream().map(a -> a.getId()).collect(Collectors.toList());
		List<Integer> hashTagIds = hashTagEntities.stream().map(HashTagEntity::getId).collect(Collectors.toList());
		List<TagUserTotal> list = userHashTagRepository.countAllUser(hashTagIds);

		Map<Integer, Long> map = list.stream().collect(Collectors.toMap(TagUserTotal::getHashTagId, TagUserTotal::getTotalUser));

		for (HashTagEntity hashTagEntity : hashTagEntities) {
			int id = hashTagEntity.getId();
			HashTagDTO hashTagDTO = new HashTagDTO();

			BeanUtils.copyProperties(hashTagEntity, hashTagDTO);
			hashTagDTO.setNumberOfUser(Optional.ofNullable(map.get(id)).orElse(0L)); // map.get(id) trả về value; value
																						// là getTotalUser
			hashTagDTO.setName2(hashTagDTO.getName().replaceAll("\\s", ""));
			responseHashTagDTOs.add(hashTagDTO);

		}

		return responseHashTagDTOs;
	}

	@Override
	public List<HashTagDTO> findMaxUserInHashTag() {
		List<HashTagDTO> responseHashTag = new ArrayList<>();
		List<TagUserTotal> listHashTag = userHashTagRepository.findMaxUserInHashTag();
		
		Map<Integer, Long> map = listHashTag.stream().collect(Collectors.toMap(TagUserTotal::getHashTagId, TagUserTotal::getMaxUser));
		
		Set<Integer> set = map.keySet();
        for (int key : set) {
            HashTagDTO dto = new HashTagDTO();
            dto.setId(key);
            dto.setNumberOfUser(map.get(key));
            responseHashTag.add(dto);
        }
        
        
		return responseHashTag;
	}

}
