package ddvudo.web.service.impl;

//@service("FileService")
//@Transactional
public class FileServiceImpl
//		implements FileService
{
//	UploadFileMapper fileMapper;
//	FileStorageUtil fileStorageUtil;
//
//	public FileServiceImpl(UploadFileMapper fileMapper, FileStorageUtil fileStorageUtil) {
//		this.fileMapper = fileMapper;
//		this.fileStorageUtil = fileStorageUtil;
//	}
//
//	@Override
//	public UploadFile saveOne(MultipartFile file, User user) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidArgumentException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException {
//		UploadFile uploadFile = new UploadFile();
//		uploadFile.setOwner(user.getName());
//		uploadFile.setOwnerid(user.getId());
//		uploadFile.setCreatetime(new Date());
//		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//		String fileExtendName = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
//		String randomFileName = uuid + fileExtendName;
//		String folder = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
//		uploadFile.setFilename(folder + "/" + randomFileName);
//		uploadFile.setRealname(file.getOriginalFilename());
//		fileStorageUtil.saveOne(file, uploadFile, user);
//		fileMapper.insertSelective(uploadFile);
//		Assert.notNull(uploadFile.getId(), "文件插入出现问题，数据库插入回显id为null");
//		return uploadFile;
//	}
//
//	@Override
//	public String getURLById(Integer id) throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException {
//		UploadFile uploadFile = fileMapper.selectByPrimaryKey(id);
//		Assert.notNull(uploadFile, "文件不存在");
//		return fileStorageUtil.getUrl(uploadFile);
//	}
}
