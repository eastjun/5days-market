/**
 * @license Copyright (c) 2003-2023, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	
	// 이미지 리사이징 플러그인 활성화
	  config.extraPlugins = 'image2';

	  // 이미지 최대 너비 지정
	  config.image2_maxWidth = 800; // 원하는 최대 너비로 변경

	  // 파일 브라우저 업로드 URL 설정 (이미지 업로드를 위해 필요한 부분)
	  config.filebrowserUploadUrl = '/adm/fileupload.do'; // 실제 업로드 URL로 변경
	};
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';

