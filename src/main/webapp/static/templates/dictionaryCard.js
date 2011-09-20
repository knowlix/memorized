	<div class="title bg-blue">
		<h3><%= title %></h3>
		<blockquote><%= description %></blockquote>
	</div>
	<div class="tools">
		<ul class="horizontal left">
			<li class="action_show_all_memas selected">все</li>
			<li class="action_show_today_memas">сегодня</li>
		</ul>
		<ul class="horizontal right">
			<li class="action_add_mema">добавить</li>
			<li class="action_shuffle_mema">перемешать</li>
			<li class="action_invert_mema">инвертировать</li>
		</ul>
	</div>
	
    <form class="form_add_mema">
    	<input type="hidden" name="memaId" value=""/>
        <div class="tb with-title-label">
        	<input type="text" name="title" value=""/>
        	<span>Слово:</span>
      	</div>
        <div class="tb">
            <textarea name="description"></textarea>
            <span>Перевод:</span>
        </div>
    </form>
	
	<ol class="memas">
		<% for(var i=0; i < memas.length; i++) { %>
			<li id="mema<%= memas[i].memaId %>">
				<p class="title"><%= memas[i].title %></p>
				<div class="description"><%= memas[i].description %></div>
			</li>
		<% } %>
	</ol>
	