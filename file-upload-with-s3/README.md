## AWS S3 API를 이용한 파일 업로드 구현

*NCP의 오브젝트 스토리지는 AWS의 S3 API를 그대로 지원하기 때문에 기존의 S3를 사용하는 서비스들도 쉽게 마이그레이션 할 수 있다.*

<br>

## Configurations
* 파일 업로드 설정 
```yaml
spring:
  servlet:
    multipart:
      enabled: true
      max-file-size: 200MB
      max-request-size: 215MB
```

<br>

* 클라우드 스토리지 관련 설정
```yaml
aws:
  s3:
    access-key: SECRET
    secret-key: SECRET
    region: kr-standard
    bucket: SECRET
    upload-path: images/
    end-point: https://kr.object.ncloudstorage.com
```

<br>

## Implementation
* 파일의 식별자 추출하기 
  * 스프링에서 제공하는 API를 이용하여 간단하게 식별자를 추출할 수 있다.
```java
StringUtils.getFilenameExtension(Objects.requireNonNull(file.getOriginalFilename()));
```

* 클라이언트 정보 가져오기 
  * 클라우드 스토리 메타데이터를 이용해서 클라이언트 정보를 가져온다.
```java
AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());

return AmazonS3ClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder
        .EndpointConfiguration(properties.getEndPoint(), properties.getRegion()))
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey())))
        .build();
```

* 파일의 입력 스트림을 가지고 `ByteArrayInputStream` 생성
```java
byte[] bytes = IOUtils.toByteArray(file.getInputStream());
metadata.setContentLength(bytes.length);
return new ByteArrayInputStream(bytes);
```


* 클라우드 스토리지 저장소에 파일 업로드하기
```java
String bucket = properties.getBucket();

ObjectMetadata metadata = new ObjectMetadata();
ByteArrayInputStream stream = getByteArrayInputStream(file, metadata);

client.putObject(new PutObjectRequest(bucket, filepath, stream, metadata)
        .withCannedAcl(CannedAccessControlList.PublicRead));

return client.getUrl(bucket, filepath).toString();
```

<br>

## Results
* 포스트맨
![postman](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F4733db0d-5e62-4e99-a942-a20d362ffc61%2FUntitled.png?table=block&id=a4a25da2-0f4a-4b3f-9153-ba427a687d2e&spaceId=7bf4105e-471a-416e-8171-751ccdb35ff5&width=2440&userId=922d7c26-714a-4479-8a78-e646e7f3e5d8&cache=v2)

* NCP 오브젝트 스토리지
![ncp-object-storage](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fcf85082a-adaa-4575-8b93-e9417fd02579%2FUntitled.png?table=block&id=0c4a2a6b-fa36-4be2-bad7-234e3cd5fd40&spaceId=7bf4105e-471a-416e-8171-751ccdb35ff5&width=3270&userId=922d7c26-714a-4479-8a78-e646e7f3e5d8&cache=v2)
