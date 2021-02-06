<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--</body>--%>
<%--<a href="some.do">跳转到some.do</a>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
            $(function () {
                $("button").click(function () {
                    $.ajax({
                        url: "myAjax2.do",
                        data: {
                            name: "foo",
                            age: "21"
                        },
                        type: "post",
                        dataType: "json",
                        success: function (resp) {
                            alert("resp==========" + resp.name + "  " + resp.age);
                        }

                    })
                });
            });
        </script>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>

    <body>
        <button>发起Ajax请求</button>
    </body>

</html>
