Form-Data Example Specification
===============================
Project Name : Rest Assured Form-Data Example
Developer    : Osanda Deshan
Version      : 1.0.0
Date         : 5/29/2018
Time         : 8:28 PM
Description  : This is an executable specification file which follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.



Get Google OAuth Access Token
-----------------------------
* Set form-data key value pairs
    |Key            |Value                                                                      |
    |---------------|---------------------------------------------------------------------------|
    |grant_type     |refresh_token                                                              |
    |client_id      |211897666304-ii96f12k6tf8h5k15sp41reo92osrgnm.apps.googleusercontent.com   |
    |client_secret  |fILuyCiaWDRElAP6MdWcJIdK                                                   |
    |refresh_token  |1/3wu2nvsunxK3mYRDTPun_8t8GeBEKkY--LYfEr9SIvU                              |
* Invoke form-data API with plain text



File upload API
---------------
* Set form-data key value pairs
    |Key                |Value                             |
    |-------------------|----------------------------------|
    |title              |DOCX file                         |
    |creatorId          |Osanda                            |
    |creatorPlatform    |Web                               |
    |creatoredSource    |File                              |
    |creatoredType      |Auto                              |
    |deckId             |5b123fa92e02d85d8b54c7b6          |
    |isExpert           |false                             |
    |examDate           |                                  |
    |userId             |osanda                            |
* Invoke form-data API with multipart file