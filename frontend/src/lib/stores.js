import { writable } from 'svelte/store';

export const workspace = writable('add');
export const tags = writable([]);
export const selectedTid = writable(0);
export const costs = writable([]);
export const editCost = writable({});

