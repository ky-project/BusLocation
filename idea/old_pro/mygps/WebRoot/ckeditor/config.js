/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	

	    // Define changes to default configuration here. For example:
	    //�������ԣ��˴���Ϊ����
	    config.language = 'zh-cn'; //����
	    //��ɫ
	    //config.uiColor = '#AADC6E';
	    //����
	    //config.font_names = '����;����;������;����;����;��Բ;΢���ź�;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;'+config.font_names;
	    //config.extraPlugins='font';
	    //config.font_names='����/����;����/����;����/����_GB2312;����/����_GB2312;����/����;��Բ/��Բ;΢���ź�/΢���ź�;'+ config.font_names;
	    config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names ;
	    //Ĭ��ʹ�õ�toolbar���������������趨����Ϊ��Basic����toolbarΪϵͳĬ�ϵĹ�����
	    config.toolbar = 'Basic';
	    //����Ϊ��Basic����toolbar�����������ľ����趨��ֻ�������¹��ܣ�
	    config.toolbar_Basic =
	    [
	        { name: 'styles', items: ['Font', 'FontSize'] }, //��ʽ�������塢��С
	        {name: 'paragraph', items: ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] }, //������������롢���Ķ��롢�Ҷ��롢���˶���
	        {name: 'colors', items: ['TextColor', 'BGColor'] }, //��ɫ�����ı���ɫ��������ɫ
	        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat'] }, //����ʽ�����Ӵ֡���б���»��ߡ�ɾ���ߡ��±ꡢ�ϱꡢ�Ƴ���ʽ
	        {name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule'] }, //��������ͼ��flash�����ˮƽ��
	        {name: 'links', items: ['Link', 'Unlink'] }, //�������������ӳ����ӡ�ȡ������
	        {name: 'document', items: ['Source']}//Դ���������鿴Դ����
	    ];
	    //�������Ƿ���Ա��������������Ͻǵ���Ƿ���Ƿ���ʾ��
	    config.toolbarCanCollapse = true;
	    //������Ĭ���Ƿ�չ��
	    config.toolbarStartupExpanded = true;
	    // �Ƿ����?��ק�ı�ߴ硱���ܣ��������½ǵ���Ƿ���Ƿ���ʾ��
	    config.resize_enabled = false;
	    //�����룺shift+Enterʱ����ı�ǩ
	    config.shiftEnterMode = CKEDITOR.ENTER_P; //��ѡ��CKEDITOR.ENTER_BR��CKEDITOR.ENTER_DIV
	    //�س���Enter��ʱ����ı�ǩ
	    config.enterMode = CKEDITOR.ENTER_BR; //���x��CKEDITOR.ENTER_BR��CKEDITOR.ENTER_DIV
	    //���
	    config.width = "900px";
	    //�߶�
	    config.height = "400px";
	    //Ĭ����ʽ
	    //config.skin ��'kama'��Ĭ�ϣ���'office2003'��'v2'
	    //config.skin = "kama";
	    //��������λ��
	    //config.toolbarLocation = 'top'; //��ѡ��bottom
	    //�ı��С�����߶�
	    //config.resize_maxHeight = 3000;
	    //�ı��С�������
	    //config.resize_maxWidth = 3000;
	    //�ı��С����С�߶�
	    //config.resize_minHeight = 250;
	    //�ı��С����С���
	    //config.resize_minWidth = 750;
	    //���ύ���д˱༭���ı?ʱ���Ƿ��Զ�����Ԫ�؃ȵ�����
	    //config.autoUpdateElement = true;
	    //���Ŀ¼�������Ŀ¼��Ϊ��Ϊ���Ŀ¼
	    //config.baseHref = ''
	    //�༭����z-indexֵ
	    //config.baseFloatZIndex = 10000;

	    //����Ϊ�ϴ�������������ã������ckfinder�ؼ�ʹ��
	    //    var ckfinderPath = "/Scripts";
	    //    config.filebrowserUrl = ckfinderPath + '/ckfinder/ckfinder.html';//�ϴ��ļ�ʱ��������ļ���
	    //    config.filebrowserImageBrowseUrl = ckfinderPath + '/ckfinder/ckfinder.html?Type=Images';//�ϴ�ͼƬʱ��������ļ���
	    //    config.filebrowserFlashBrowserUrl = ckfinderPath + '/ckfinder/ckfinder.html?Type=Flash'; //�ϴ�Flashʱ��������ļ���
	    //    config.filebrowserUploadUrl = ckfinderPath + '/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Files'; //�ϴ��ļ���ť(��ǩ)
	    //    config.filebrowserImageUploadUrl = ckfinderPath + '/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Images';//�ϴ�ͼƬ��ť(��ǩ)
	    //    config.filebrowserFlashUploadUrl = ckfinderPath + '/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Flash';//�ϴ�Flash��ť(��ǩ)
		
};
