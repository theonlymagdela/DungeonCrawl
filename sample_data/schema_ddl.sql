DROP TABLE IF EXISTS public.inventory;
DROP TABLE IF EXISTS public.game_state;
DROP TABLE IF EXISTS public.player;

CREATE TABLE public.player (
                               id serial NOT NULL PRIMARY KEY,
                               player_name text NOT NULL ,
                               hp integer NOT NULL,
                               sp decimal,
                               dp decimal,
                               x integer NOT NULL,
                               y integer NOT NULL
);

CREATE TABLE public.game_state (
                                   id serial NOT NULL PRIMARY KEY,
                                   saved_name text NOT NULL,
                                   current_map text NOT NULL,
                                   saved_at_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   player_id integer NOT NULL
);

CREATE TABLE public.inventory (
                                  id serial NOT NULL PRIMARY KEY,
                                  game_state_id integer NOT NULL,
                                  item_name text
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);