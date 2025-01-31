package com.lion.finalprojectshoppingmallservice3team.customer.data.util

// 사용자 상태
enum class UserState(val number:Int, val str:String){
    // 정상
    USER_STATE_NORMAL(1, "정상"),
    // 탈퇴
    USER_STATE_SIGNOUT(2, "탈퇴")
}

// 로그인 결과
enum class LoginResult(val number:Int, val str:String){
    LOGIN_RESULT_SUCCESS(1, "로그인 성공"),
    LOGIN_RESULT_ID_NOT_EXIST(2, "존재하지 않는 아이디"),
    LOGIN_RESULT_PASSWORD_INCORRECT(3, "잘못된 비밀번호"),
    LOGIN_RESULT_SIGNOUT_MEMBER(4, "탈퇴한 회원"),
}

// 상품 판매상태
enum class SellingState(val number:Int, val str:String){
    // 판매중
    SELLING_STATE_SELLING(1, "판매중"),
    // 판매완료
    SELLING_STATE_SOLD_OUT(2, "판매완료"),
}

// 상품 공개여부
enum class OpenState(val number:Int, val str:String){
    // 공개
    SELLING_STATE_OPEN(1, "공개"),
    // 비공개
    SELLING_STATE_PRIVATE(2, "비공개"),
}

// KC 및 기타 인증
enum class Certification(val number:Int, val str:String){
    // 인증 있음
    CERTIFICATION_YES(1, "인증 있음"),
    // 인증 없음
    CERTIFICATION_NO(2, "인증 없음"),
}
// 상품 카테고리
enum class ProductCategory(val str : String){
    // 전체 상품
    PRODUCT_CATEGORY_ALL("전체 상품"),
    // 의류
    PRODUCT_CATEGORY_CLOTHING("의류"),
    // 굿즈
    PRODUCT_CATEGORY_GOODS("굿즈"),
    // 패션잡화
    PRODUCT_CATEGORY_FASHION_ACCESSORIES("패션잡화"),
    // 쿠션/패브릭
    PRODUCT_CATEGORY_CUSHION_FABRIC("쿠션/패브릭"),
    // 문구/오피스
    PRODUCT_CATEGORY_OFFICE_SUPPLIES("문구/오피스"),
    // 폰액세서리
    PRODUCT_CATEGORY_PHONE_ACCESSORIES("폰액세서리"),
    // 스티커/지류
    PRODUCT_CATEGORY_STICKER_PAPER("스티커/지류"),
    // 리빙
    PRODUCT_CATEGORY_LIVING("리빙"),
}

// 상품 소카테고리
enum class ProductSubCategory(val str : String){
    // 전체
    PRODUCT_SUB_CATEGORY_ALL("전체"),
    // 티셔츠
    PRODUCT_SUB_CATEGORY_TSHIRT("티셔츠"),
    // 맨투맨
    PRODUCT_SUB_CATEGORY_SWEATSHIRT("맨투맨"),
    // 후드
    PRODUCT_SUB_CATEGORY_HOODIE("후드"),
    // 아크릴 굿즈
    PRODUCT_SUB_CATEGORY_ACRYL_GOODS("아크릴 굿즈"),
    // 키링
    PRODUCT_SUB_CATEGORY_KEYRING("키링"),
    // 거울/핀버튼
    PRODUCT_SUB_CATEGORY_MIRROR_BUTTON("거울/핀버튼"),
    // 가방
    PRODUCT_SUB_CATEGORY_BAG("가방"),
    // 쿠션/방석
    PRODUCT_SUB_CATEGORY_CUSHION("쿠션/방석"),
    // 마우스패드
    PRODUCT_SUB_CATEGORY_MOUSE_PAD("마우스패드"),
    // 스마트톡
    PRODUCT_SUB_CATEGORY_SMART_TOK("스마트톡"),
    // 카드
    PRODUCT_SUB_CATEGORY_CARD("카드"),
    // 머그컵
    PRODUCT_SUB_CATEGORY_MUG("머그컵"),
}
object CategoryMapping {
    // 불변 Map 사용
    val categoryToSubCategory: Map<String, List<String>> = mapOf(
        // 의류
        ProductCategory.PRODUCT_CATEGORY_CLOTHING.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_TSHIRT.str,
            ProductSubCategory.PRODUCT_SUB_CATEGORY_SWEATSHIRT.str,
            ProductSubCategory.PRODUCT_SUB_CATEGORY_HOODIE.str
        ),
        // 굿즈
        ProductCategory.PRODUCT_CATEGORY_GOODS.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_ACRYL_GOODS.str,
            ProductSubCategory.PRODUCT_SUB_CATEGORY_KEYRING.str,
            ProductSubCategory.PRODUCT_SUB_CATEGORY_MIRROR_BUTTON.str
        ),
        // 패션잡화
        ProductCategory.PRODUCT_CATEGORY_FASHION_ACCESSORIES.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_BAG.str
        ),
        // 쿠션/패브릭
        ProductCategory.PRODUCT_CATEGORY_CUSHION_FABRIC.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_CUSHION.str
        ),
        // 문구/오피스
        ProductCategory.PRODUCT_CATEGORY_OFFICE_SUPPLIES.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_MOUSE_PAD.str
        ),
        // 폰액세서리
        ProductCategory.PRODUCT_CATEGORY_PHONE_ACCESSORIES.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_SMART_TOK.str
        ),
        // 스티커/지류
        ProductCategory.PRODUCT_CATEGORY_STICKER_PAPER.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_CARD.str
        ),
        // 리빙
        ProductCategory.PRODUCT_CATEGORY_LIVING.str to listOf(
            ProductSubCategory.PRODUCT_SUB_CATEGORY_MUG.str
        )
    )

    // "전체" 카테고리 시 모든 서브 카테고리 반환
    fun getSubCategoriesForCategory(category: String): List<String> {
        return if (category == ProductCategory.PRODUCT_CATEGORY_ALL.str) {
            ProductSubCategory.values().map { it.str }
        } else {
            categoryToSubCategory[category] ?: emptyList()
        }
    }
}
