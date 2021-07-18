package com.ispace.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private String accessToken = "Bearer eyJraWQiOiJPTllnQldOb0RoLVIyUzFFUWpQNWltTzNTV2JQRi1ubV9DRVBFN3RwQlNVIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlpRQVo0N3d1SjNuLU5xX0FMWkVuRnJpeEVxeThwTDNDbF9tVWdEblExQ1UiLCJpc3MiOiJodHRwczovL2Rldi04MzI1MDM2Mi5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2MjU1OTcxNTMsImV4cCI6MTYyNTYwMDc1MywiY2lkIjoiMG9heGU1ZTRmZHpPRTRxem41ZDYiLCJ1aWQiOiIwMHUxNWRmMjI3bWFIdmFraTVkNyIsInNjcCI6WyJwcm9maWxlIiwib3BlbmlkIiwiZW1haWwiXSwic3ViIjoidGVzdDI3QGdtYWlsLnRlc3QuY29tIn0.DDQ72J01klPybwD0Mdb1l2ij1B9uEHbkDEYnbNYFagsCFGLNlCZ67sE0ADHFDtJfE1kOyszDn7sH5_w8pQTPKIalRpRfNFU-74o82btmmqK9LrXFK5Qkt9_uYZAiqumNEqZxAbLuSOFG5gArfrUbzK_6nn4I-ORrt40ThNPGTkXcqihUhp3bqZHx4eXnAvCr7YaLYVXXbzh-G7u4z6PCWOH66iDoc_HyEhW14aLdNIMa2V8JwrUf-avmLkQluzcBAVW0UZxIQTYigiylnQ9LNRmcUcrqVIKIQWE5tzRxOMXPA5VQrHnttD31ec39zIq_Vt3OpByqbnrT16yFD-oenw";

    @Test
    void decodeJwtToken() {
    }

    @Test
    void getCurrentUserPayload() {
    }

    @Test
    void canGetCurrentUserEmailFromAuthorization() {
        // given
        // when
        String result = JwtUtil.getCurrentUserEmailFromAuthorization(accessToken);
        // then
        assertThat(result).isEqualTo("test27@gmail.test.com");
    }
}