# Embed Domo Java Example

This is an example of how to distribute Embed Domo Widgets using Java so it can
control the authentication and authorization out of Domo. There are similar
examples for other languages in the [official Embed Domo documentation](https://developer.domo.com/docs/embed/embed-2).


## Run and test

Edit `application.yml` with your client-in and secret.

Launch it with `mvn spring-boot:run`.

Call the API with the EmbedID of your widget:

```sh
curl localhost:8080/domo/ej73l
```

You can use the `authorization` field to show the widget. For example, using
this HTML template replacing <EMBED-ID> and >AUTHORIZATION-TOKEN>:

```html
<html>
    <body>
      <form id="form" action="https://public.domo.com/cards/<EMBED-ID>" method="post">
        <input type="hidden" name="embedToken" value='<AUTHORIZATION-TOKEN>'>

      </form>
      <script>
        document.getElementById("form").submit();
      </script>
    </body>
  </html>
```

Open it with your browser and it should show the Domo widget.
